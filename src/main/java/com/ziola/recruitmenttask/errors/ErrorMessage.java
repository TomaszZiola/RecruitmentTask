package com.ziola.recruitmenttask.errors;

import lombok.Data;

import java.time.LocalDate;
/**
 * Simple class that returns the error message. Can be easily extended to return more information if needed by the client.
 */
@Data
public class ErrorMessage {

    String message;
    LocalDate timestamp;
}
