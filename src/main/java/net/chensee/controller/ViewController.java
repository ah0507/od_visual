package net.chensee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @author ah
 * @title: ViewController
 * @date 2020/1/10 17:08
 */
@Controller
@RequestMapping(value = "/")
public class ViewController {

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }

    @RequestMapping("loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login";
    }

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(HttpSession session){
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "paramsManage", method = RequestMethod.GET)
    public String getForm(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        } else {
            return "paramsManage";
        }
    }

    @RequestMapping(value = "loggingManage", method = RequestMethod.GET)
    public String loggingManage(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        } else {
            return "loggingManage";
        }
    }

    @RequestMapping(value = "userManage", method = RequestMethod.GET)
    public String userManage(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        } else {
            return "userManage";
        }
    }

    @RequestMapping(value = "/group/dailyView", method = RequestMethod.GET)
    public String groupDailyView(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        } else {
            return "group/DailyView";
        }
    }

    @RequestMapping(value = "/group/monthView", method = RequestMethod.GET)
    public String groupMonthView(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        } else {
            return "group/MonthView";
        }
    }

    @RequestMapping(value = "/group/weekView", method = RequestMethod.GET)
    public String groupWeekView(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        } else {
            return "group/WeekView";
        }
    }

    @RequestMapping(value = "company/dailyView", method = RequestMethod.GET)
    public String companyDailyView(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        } else {
            return "company/DailyView";
        }
    }

    @RequestMapping(value = "company/monthView", method = RequestMethod.GET)
    public String companyMonthView(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        } else {
            return "company/MonthView";
        }
    }

    @RequestMapping(value = "company/weekView", method = RequestMethod.GET)
    public String companyWeekView(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        } else {
            return "company/WeekView";
        }
    }

    @RequestMapping(value = "line/dailyView", method = RequestMethod.GET)
    public String lineDailyView(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        } else {
            return "line/DailyView";
        }
    }

    @RequestMapping(value = "line/monthView", method = RequestMethod.GET)
    public String lineMonthView(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        } else {
            return "line/MonthView";
        }
    }

    @RequestMapping(value = "line/weekView", method = RequestMethod.GET)
    public String lineWeekView(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        } else {
            return "line/WeekView";
        }
    }
}
