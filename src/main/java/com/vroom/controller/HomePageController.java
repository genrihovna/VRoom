package com.vroom.controller;

import com.vroom.service.VRoomService;
import com.vroom.model.rooms.VRoom;
import com.vroom.model.VRoomAttributeGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class HomePageController {

    @Autowired
    private VRoomService vRoomService;

    //all attributes that are available for selection
    @Autowired
    private VRoomAttributeGroups vRoomAttributeGroups;

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
        List<String> templates= vRoomService.addVRoomService(requestParams);

        model.addAttribute("components",templates);
        model.addAttribute("vroom", new VRoom());
        return "addvroom";
    }

    @RequestMapping("/savevroom")
    public String saveVRoom(VRoom vRoom,
                            @RequestParam Map<String, String> requestParams){
        vRoomService.saveVRoomService(vRoom,requestParams);
        return "savevroom";
    }

    @RequestMapping(value = "/generateJSON", method = RequestMethod.GET,
                    produces = "application/json")
    public @ResponseBody String generateJSON(){

        return vRoomService.generateJSON();
    }
}

