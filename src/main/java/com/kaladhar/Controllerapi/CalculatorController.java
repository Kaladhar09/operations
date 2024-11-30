package com.kaladhar.Controllerapi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @GetMapping("/calculate")
    public String showForm() {
        return "calculator"; // Return the Thymeleaf template (calculator.html)
    }

    @RequestMapping("/calculate")
    public String calculate(@RequestParam("num1") double num1,
                            @RequestParam("num2") double num2,
                            @RequestParam("operator") String operator,
                            Model model) {
        double result = 0;

        // Perform the calculation based on the operator
        switch (operator) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    model.addAttribute("error", "Division by zero is not allowed.");
                    return "calculator";
                }
                break;
            default:
                model.addAttribute("error", "Invalid operator.");
                return "calculator";
        }

        model.addAttribute("result", result);
        return "calculator";
    }
}
