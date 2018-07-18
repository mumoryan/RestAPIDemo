package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")  // Can map request, map all "/" and "/asydsa/ashdgs/..." here
public class controller {

    @GetMapping(path = "/challenge2")   // Others include PostMappig...etc
    public ResponseEntity<?> job1(@RequestParam("methodName") String methodName, @RequestParam("input") String input) {
        if (methodName == null || input == null) return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        String response = "";
        switch (methodName){
            case "stringClean":
                response = stringClean(input);
                break;
            case "maxBlock":
                response = maxBlock(input);
                break;
            case "reorderBlock":
                response = reorderBlock(input);
                break;
            default:
                response = "";
                break;
        }
        return new ResponseEntity<>(response, HttpStatus.OK);  // OK: 200, Not found: 404, No content: 204
    }

    // First problem
    public String stringClean(String s){
        if (s==null || s.length()<1) return "";
        StringBuilder sb = new StringBuilder();
        char prev = s.charAt(0);
        sb.append(prev);
        for (int i = 1; i < s.length(); i++){
            char cur = s.charAt(i);
            if (cur!=prev){
                sb.append(cur);
                prev = cur;
            }
        }
        return sb.toString();
    }

    // Second problem
    public String maxBlock(String s){
        if (s==null || s.length()<1) return ""+ 0;
        int max = 1, cur = 1;

        for (int i = 1; i < s.length(); i++){
            if (s.charAt(i) == s.charAt(i-1)){
                cur++;
            }else{
                max = Math.max(max, cur);
                cur = 1;
            }
        }
        max = Math.max(max, cur);
        return ""+max;
    }

    // Third problem
    public String reorderBlock(String s){
        if (s==null || s.length()<1) return "";
        int[] chars = new int[52];
        for (int i = 0; i < s.length(); i++){
            char cur = s.charAt(i);
            if (cur-'A'>=0 && cur-'A'<25){  // Uppercase
                chars[cur-'A']++;
            }else{   // Lowercase
                chars[cur-'a'+26]++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++){
            for (int freq = chars[i]; freq>0; freq--){
                sb.append((char)('A'+i));
            }
            for (int freq = chars[26+i]; freq>0; freq--){
                sb.append((char)('a'+i));
            }
        }
        return sb.toString();
    }

}
