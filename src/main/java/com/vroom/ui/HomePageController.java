package com.vroom.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class HomePageController {

    //all attributes that are available for selection
    Map<String, VRoomDecorator> allRoomDecorators;
    //the decorators that apply for our rooms
    private List<VRoomDecorator> vRoomDecorators;

    public HomePageController(){
        vRoomDecorators=new ArrayList<VRoomDecorator>();
        allRoomDecorators=new HashMap<String, VRoomDecorator>();

        //instantiate each of our decorators and add them
        //TODO: move this to a Spring configuration file.
        PrivateRoom privateRoom = new PrivateRoom();
        BusinessRoom businessRoom = new BusinessRoom();
        ThirdRoom thirdRoom = new ThirdRoom();

        allRoomDecorators.put(privateRoom.getLabel(), privateRoom);
        allRoomDecorators.put(businessRoom.getLabel(), businessRoom);
        allRoomDecorators.put(thirdRoom.getLabel(), thirdRoom);
    }

    @RequestMapping("/home")
    public String home(@RequestParam(value="name", required=false,
            defaultValue="Friend") String name, Model model) {
        model.addAttribute("name", name);
        return "home";
    }

    @RequestMapping("/selectattributes")
    public String selectAttributes(Model model){
        //pass all possible decorators to our form.
        model.addAttribute("components", allRoomDecorators.keySet());
        return "selectattributes";
    }

    @RequestMapping("/addvroom")
    public String addVRoom(@RequestParam Map<String, String> requestParams,
                           Model model){
        //get all keys of the selected attributes
        Set<String> selectedKeys = requestParams.keySet();

        //a collection of templates that correspond to the user's selection
        List<String> templates=new ArrayList<String>();

        //iterate over the selected keys, and get the template identifier.
        for (String key: selectedKeys) {
            VRoomDecorator vRoomDecorator = allRoomDecorators.get(key);
            //a collection containing only the selected decorators.
            vRoomDecorators.add(vRoomDecorator);
            if(vRoomDecorator!=null){
                String template = vRoomDecorator.getTemplate();
                templates.add(template);
            }
        }
        VRoom v = new VRoom();
        model.addAttribute("components",templates);
        model.addAttribute("vroom", v);
        v.print();
        return "addvroom";
    }

    @RequestMapping("/savevroom")
    public String saveVRoom(VRoom vRoom,
                            @RequestParam Map<String, String> requestParams){
        //implement the command pattern to process our decorators.
        //iterate over our decorators and invoke the command method
        for (VRoomDecorator v: vRoomDecorators) {
            v.processSubmission(requestParams, vRoom);
        }
        return "savevroom";
    }
}

