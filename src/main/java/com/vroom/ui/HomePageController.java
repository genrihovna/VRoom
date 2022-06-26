package com.vroom.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

@Controller
@ImportResource({"/WEB-INF/classes/applicationContext.xml"})
public class HomePageController {

    //all attributes that are available for selection
    @Autowired
    private VRoomAttributeGroups vRoomAttributeGroups;

    //the decorators that apply for our rooms
    private List<VRoomDecorator> vRoomDecorators;

    public HomePageController(){
        vRoomDecorators=new ArrayList<VRoomDecorator>();
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
        model.addAttribute("components", vRoomAttributeGroups
                .getDecorators().keySet());
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
            VRoomDecorator vRoomDecorator = vRoomAttributeGroups
                    .getDecorators().get(key);
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

