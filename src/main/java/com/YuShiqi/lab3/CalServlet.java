package com.YuShiqi.lab3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CalServlet", value = "/lab3/cal")
public class CalServlet extends HttpServlet {
    private int add(int firstVal,int sendVal){
        return  firstVal+sendVal;

    }
    private int sub(int firstVal,int sendVal){
        return  firstVal-sendVal;
    }
    private int mul(int firstVal,int sendVal){
        return  firstVal*sendVal;
    }
    private int div(int firstVal,int sendVal){


        return  firstVal/sendVal;
    }
    private int computer_length(String name){
        return name.length();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action="";
        int result=0;
        int length=0;

        int firstVal = Integer.parseInt(request.getParameter("firstval"));
        int secondVal = Integer.parseInt(request.getParameter("secondval"));
        String name=request.getParameter("name");

        if(request.getParameter("add")!=null){
            action="add";
            result=add(firstVal,secondVal);
        }else if(request.getParameter("sub")!=null){
            action="sub";
            result=sub(firstVal,secondVal);
        }else if(request.getParameter("mul")!=null){
            action="mul";
            result=mul(firstVal,secondVal);
        }else if(request.getParameter("div")!=null){
            action="div";
            result=div(firstVal,secondVal);
        }else if(request.getParameter("length")!=null){
            action="computer length";
            length=computer_length(name);
        }


        if(action.equals("add") || action.equals("sub") || action.equals("mul") || action.equals("div")){
            Cookie cfirstVal=new Cookie("firstval",String.valueOf(firstVal));
            Cookie csecondVal=new Cookie("secondval",String.valueOf(secondVal));
            Cookie cresult=new Cookie("result",String.valueOf(result));
            cfirstVal.setMaxAge(10);
            csecondVal.setMaxAge(10);
            cresult.setMaxAge(10);

            response.addCookie(cfirstVal);
            response.addCookie(csecondVal);
            response.addCookie(cresult);

        }
        else if(action.equals("computer length")) {
            Cookie cname=new Cookie("name",name);
            Cookie clength=new Cookie("length",String.valueOf(length));
            cname.setMaxAge(10);
            clength.setMaxAge(10);

            response.addCookie(cname);
            response.addCookie(clength);

        }
        response.sendRedirect("cal.jsp");

    }
}