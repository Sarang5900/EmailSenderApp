package com.email.controller;

import com.email.entity.EmailRequestEntity;
import com.email.entity.EmailResponse;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    EmailService emailService;

    //api to send mail
    //@PostMapping("/sendEmail")
    @RequestMapping(path = "/sendEmail", method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequestEntity emailRequest){

        System.out.println(emailRequest);
        boolean response = this.emailService.sendEmail(emailRequest.getMessage(), emailRequest.getSubject(), emailRequest.getTo());
        if(response){
            return ResponseEntity.ok(new EmailResponse("Email Sent Successfully!!"));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not sent!!"));
        }
    }
}
