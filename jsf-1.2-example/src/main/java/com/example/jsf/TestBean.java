package com.example.jsf;

import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

public class TestBean {

    public void go(ActionEvent actionEvent) {
        String input = null;

        FacesContext fc = FacesContext.getCurrentInstance();
        ExpressionFactory ef = fc.getApplication().getExpressionFactory();
        Class[] paramTypes = new Class[] { String.class };
        Object[] values = new Object[] { input };

        System.out.println("Calling method with null input String");
        String expression = "#{testBean.testcase}";
        MethodExpression me = ef.createMethodExpression(fc.getELContext(), expression, null, paramTypes);
        Object result = me.invoke(FacesContext.getCurrentInstance().getELContext(), values);
    }

    public String testcase(String in) {
        if (in == null) {
            System.out.println("Yay - input parameter is still null");
        } else {
            System.out.println("Boo!  Input parameter is not null");
            if (in.isEmpty()) {
                System.out.println("input parameter is \"\"");
            }
        }
        return in;
    }
}
