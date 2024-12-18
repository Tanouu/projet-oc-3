package com.tanou.projet.oc.backend.projet2.exception;

public class PictureStorageException extends RuntimeException {
  public PictureStorageException(String message) {
    super(message);
  }

  public PictureStorageException(String message, Throwable cause) {
    super(message, cause);
  }
}
