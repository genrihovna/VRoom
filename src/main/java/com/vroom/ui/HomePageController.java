package com.vroom.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomePageController {

    //all attributes that are available for selection

    //the decorators that apply for our rooms
    private List<VRoomDecorator> vRoomDecorators;
    Map<String, VRoomDecorator> allRoomDecorators;
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
        //pass all possible decorators to our room.
        model.addAttribute("components", allRoomDecorators.keySet());
        return "selectattributes";
    }

    @RequestMapping("/addvroom")
    public String addVRoom(@RequestParam Map<String, String> requestParams,
                           Model model){
        model.addAttribute("components", allRoomDecorators.keySet());
        model.addAttribute("vroom", new VRoom());
        return "addvroom";
    }

    @RequestMapping("/savevroom")
    public String savePlant(VRoom vRoom){
        return "savevroom";
    }
}

