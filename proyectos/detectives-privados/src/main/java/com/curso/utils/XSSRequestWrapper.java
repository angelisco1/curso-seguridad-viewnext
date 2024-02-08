package com.curso.utils;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.owasp.esapi.ESAPI;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

	public XSSRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		String value = super.getHeader(name);
		System.out.println("[+] Get header 1: " + value);
		String strippedValue = stripXSS(value);
		System.out.println("[+] Get header 2: " + strippedValue);
		return strippedValue;
	}


	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		String value = super.getParameter(name);
		System.out.println("[+] Get parameter 1: " + value);
		String strippedValue = stripXSS(value);
		System.out.println("[+] Get parameter 2: " + strippedValue);
		return strippedValue;
	}


	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		String[] values = super.getParameterValues(name);
		
		if (values == null) {
			return null;
		}
		
		Integer count = values.length;
		String[] encodedValues = new String[count];
		for (Integer i = 0; i < count; i++) {
			encodedValues[i] = stripXSS(values[i]);
		}
		
		return encodedValues;
 	}



	private String stripXSS(String value) {
        if (value != null) {
            value = ESAPI.encoder().canonicalize(value);
            
            //Este ejemplo ser�a otra alternativa al ESAPI para evitar el 
            //XSS, pasa todos los barridos necesarios para evitar la inyecccion
            
            // Avoid null characters
            value = value.replaceAll("\0", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid anything in a src='...' type of expression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid expression(...) expressions
            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid onload= expressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
        }
        return value;
    }
}
