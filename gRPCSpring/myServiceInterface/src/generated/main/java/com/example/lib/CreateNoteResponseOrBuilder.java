// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: myService.proto

package com.example.lib;

public interface CreateNoteResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.example.CreateNoteResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bool success = 1;</code>
   * @return The success.
   */
  boolean getSuccess();

  /**
   * <code>string message = 2;</code>
   * @return The message.
   */
  java.lang.String getMessage();
  /**
   * <code>string message = 2;</code>
   * @return The bytes for message.
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <code>string note_id = 3;</code>
   * @return The noteId.
   */
  java.lang.String getNoteId();
  /**
   * <code>string note_id = 3;</code>
   * @return The bytes for noteId.
   */
  com.google.protobuf.ByteString
      getNoteIdBytes();
}
