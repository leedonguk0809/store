package com.example.store.common;

import com.example.store.exception.StoreException;
import com.example.store.exception.payment.StockIsEmpty;
import com.example.store.request.item.ItemSearch;
import com.example.store.response.ErrorResponse;
import com.example.store.response.ItemResponse;
import com.example.store.response.Paging;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Store;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(StoreException.class)
    public ModelAndView storeException(StoreException e, RedirectAttributes redirectAttributes) {
        int statusCode = e.getStatusCode();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();
        log.error("HTTP Status {} [StoreException : {}]", statusCode ,e.getMessage());
        if (auth != null && auth.isAuthenticated()) {
            // 인증 정보가 유효한 경우 인증 정보를 유지하면서 메인 화면으로 리다이렉트
            SecurityContextHolder.getContext().setAuthentication(auth);
            ModelAndView modelAndView = new ModelAndView("redirect:/");
            return modelAndView;
        }
        return new ModelAndView("redirect:/");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();

        for (FieldError fieldError : e.getFieldErrors()) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return response;
    }

    @ExceptionHandler(StockIsEmpty.class)
    public void handleStockIsEmptyException(StockIsEmpty e, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException {
        // 에러 로깅
        log.error("[StockIsEmpty Exception] {}", e.getMessage());

        redirectAttributes.addFlashAttribute("errorMessage", e.getEmptyItem());

        // 리다이렉트할 경로 설정
        response.sendRedirect(request.getContextPath() + "/orders/error");
        return;
    }
}