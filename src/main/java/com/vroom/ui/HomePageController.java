package com.vroom.ui;

import com.sun.javaws.IconUtil;
import com.vroom.helper.VRoomHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    //all of the saved VRooms
    private List<VRoom> allVRooms;

    public HomePageController(){
        vRoomDecorators=new ArrayList<VRoomDecorator>();
        allVRooms = new ArrayList<VRoom>();
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

            if(vRoomDecorator!=null){
                //a collection containing only the selected decorators.
                vRoomDecorators.add(vRoomDecorator);
                String template = vRoomDecorator.getTemplate();
                templates.add(template);
            }
        }

        model.addAttribute("components",templates);
        model.addAttribute("vroom", new VRoom());
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
        //add the vroom submitted to our collection of saved vrooms
        allVRooms.add(vRoom);
        return "savevroom";
    }

    @RequestMapping(value = "/generateJSON", method = RequestMethod.GET,
                    produces = "application/json")
    public @ResponseBody String generateJSON(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "/WEB-INF/classes/applicationContext.xml");

        //String builder to contain our JSON
        StringBuilder json=new StringBuilder();

        //iterate over our collection of vrooms, and invoke the visitor.
        for (VRoom vRoom:allVRooms) {
            Map<String, String> additionalProperties = vRoom.getAdditionalProperties();
            String helperBean = additionalProperties.get(VRoom.HELPER);
            VRoomHelper vRoomHelper = context.getBean(helperBean, VRoomHelper.class);

            String vRoomJSON = vRoom.accept(vRoomHelper);
            json.append(vRoomJSON);
        }
        return json.toString();
    }
}

