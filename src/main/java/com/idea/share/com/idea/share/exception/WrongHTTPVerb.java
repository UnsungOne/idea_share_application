package com.idea.share.com.idea.share.exception;

import org.springframework.web.HttpRequestMethodNotSupportedException;

public class WrongHTTPVerb extends HttpRequestMethodNotSupportedException {
    public WrongHTTPVerb(String method) {
        super(method);
    }
}