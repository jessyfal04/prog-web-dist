package com.example.lib;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * The note service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: myService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MyServiceGrpc {

  private MyServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "com.example.MyService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.lib.CreateNoteRequest,
      com.example.lib.CreateNoteResponse> getCreateNoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateNote",
      requestType = com.example.lib.CreateNoteRequest.class,
      responseType = com.example.lib.CreateNoteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.lib.CreateNoteRequest,
      com.example.lib.CreateNoteResponse> getCreateNoteMethod() {
    io.grpc.MethodDescriptor<com.example.lib.CreateNoteRequest, com.example.lib.CreateNoteResponse> getCreateNoteMethod;
    if ((getCreateNoteMethod = MyServiceGrpc.getCreateNoteMethod) == null) {
      synchronized (MyServiceGrpc.class) {
        if ((getCreateNoteMethod = MyServiceGrpc.getCreateNoteMethod) == null) {
          MyServiceGrpc.getCreateNoteMethod = getCreateNoteMethod =
              io.grpc.MethodDescriptor.<com.example.lib.CreateNoteRequest, com.example.lib.CreateNoteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateNote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.lib.CreateNoteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.lib.CreateNoteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MyServiceMethodDescriptorSupplier("CreateNote"))
              .build();
        }
      }
    }
    return getCreateNoteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.lib.ListNotesRequest,
      com.example.lib.ListNotesResponse> getListNotesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListNotes",
      requestType = com.example.lib.ListNotesRequest.class,
      responseType = com.example.lib.ListNotesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.lib.ListNotesRequest,
      com.example.lib.ListNotesResponse> getListNotesMethod() {
    io.grpc.MethodDescriptor<com.example.lib.ListNotesRequest, com.example.lib.ListNotesResponse> getListNotesMethod;
    if ((getListNotesMethod = MyServiceGrpc.getListNotesMethod) == null) {
      synchronized (MyServiceGrpc.class) {
        if ((getListNotesMethod = MyServiceGrpc.getListNotesMethod) == null) {
          MyServiceGrpc.getListNotesMethod = getListNotesMethod =
              io.grpc.MethodDescriptor.<com.example.lib.ListNotesRequest, com.example.lib.ListNotesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListNotes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.lib.ListNotesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.lib.ListNotesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MyServiceMethodDescriptorSupplier("ListNotes"))
              .build();
        }
      }
    }
    return getListNotesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.lib.UpdateNoteThumbsUpRequest,
      com.example.lib.UpdateNoteThumbsUpResponse> getUpdateNoteThumbsUpMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateNoteThumbsUp",
      requestType = com.example.lib.UpdateNoteThumbsUpRequest.class,
      responseType = com.example.lib.UpdateNoteThumbsUpResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.lib.UpdateNoteThumbsUpRequest,
      com.example.lib.UpdateNoteThumbsUpResponse> getUpdateNoteThumbsUpMethod() {
    io.grpc.MethodDescriptor<com.example.lib.UpdateNoteThumbsUpRequest, com.example.lib.UpdateNoteThumbsUpResponse> getUpdateNoteThumbsUpMethod;
    if ((getUpdateNoteThumbsUpMethod = MyServiceGrpc.getUpdateNoteThumbsUpMethod) == null) {
      synchronized (MyServiceGrpc.class) {
        if ((getUpdateNoteThumbsUpMethod = MyServiceGrpc.getUpdateNoteThumbsUpMethod) == null) {
          MyServiceGrpc.getUpdateNoteThumbsUpMethod = getUpdateNoteThumbsUpMethod =
              io.grpc.MethodDescriptor.<com.example.lib.UpdateNoteThumbsUpRequest, com.example.lib.UpdateNoteThumbsUpResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateNoteThumbsUp"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.lib.UpdateNoteThumbsUpRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.lib.UpdateNoteThumbsUpResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MyServiceMethodDescriptorSupplier("UpdateNoteThumbsUp"))
              .build();
        }
      }
    }
    return getUpdateNoteThumbsUpMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.lib.DeleteNoteRequest,
      com.example.lib.DeleteNoteResponse> getDeleteNoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteNote",
      requestType = com.example.lib.DeleteNoteRequest.class,
      responseType = com.example.lib.DeleteNoteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.lib.DeleteNoteRequest,
      com.example.lib.DeleteNoteResponse> getDeleteNoteMethod() {
    io.grpc.MethodDescriptor<com.example.lib.DeleteNoteRequest, com.example.lib.DeleteNoteResponse> getDeleteNoteMethod;
    if ((getDeleteNoteMethod = MyServiceGrpc.getDeleteNoteMethod) == null) {
      synchronized (MyServiceGrpc.class) {
        if ((getDeleteNoteMethod = MyServiceGrpc.getDeleteNoteMethod) == null) {
          MyServiceGrpc.getDeleteNoteMethod = getDeleteNoteMethod =
              io.grpc.MethodDescriptor.<com.example.lib.DeleteNoteRequest, com.example.lib.DeleteNoteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteNote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.lib.DeleteNoteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.lib.DeleteNoteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MyServiceMethodDescriptorSupplier("DeleteNote"))
              .build();
        }
      }
    }
    return getDeleteNoteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.lib.GetNoteRequest,
      com.example.lib.GetNoteResponse> getGetNoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetNote",
      requestType = com.example.lib.GetNoteRequest.class,
      responseType = com.example.lib.GetNoteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.lib.GetNoteRequest,
      com.example.lib.GetNoteResponse> getGetNoteMethod() {
    io.grpc.MethodDescriptor<com.example.lib.GetNoteRequest, com.example.lib.GetNoteResponse> getGetNoteMethod;
    if ((getGetNoteMethod = MyServiceGrpc.getGetNoteMethod) == null) {
      synchronized (MyServiceGrpc.class) {
        if ((getGetNoteMethod = MyServiceGrpc.getGetNoteMethod) == null) {
          MyServiceGrpc.getGetNoteMethod = getGetNoteMethod =
              io.grpc.MethodDescriptor.<com.example.lib.GetNoteRequest, com.example.lib.GetNoteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetNote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.lib.GetNoteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.lib.GetNoteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MyServiceMethodDescriptorSupplier("GetNote"))
              .build();
        }
      }
    }
    return getGetNoteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MyServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MyServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MyServiceStub>() {
        @java.lang.Override
        public MyServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MyServiceStub(channel, callOptions);
        }
      };
    return MyServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MyServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MyServiceBlockingStub>() {
        @java.lang.Override
        public MyServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MyServiceBlockingStub(channel, callOptions);
        }
      };
    return MyServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MyServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MyServiceFutureStub>() {
        @java.lang.Override
        public MyServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MyServiceFutureStub(channel, callOptions);
        }
      };
    return MyServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The note service definition.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Creates a new note
     * </pre>
     */
    default void createNote(com.example.lib.CreateNoteRequest request,
        io.grpc.stub.StreamObserver<com.example.lib.CreateNoteResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateNoteMethod(), responseObserver);
    }

    /**
     * <pre>
     * Lists all notes
     * </pre>
     */
    default void listNotes(com.example.lib.ListNotesRequest request,
        io.grpc.stub.StreamObserver<com.example.lib.ListNotesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListNotesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Updates a note's thumbs up count
     * </pre>
     */
    default void updateNoteThumbsUp(com.example.lib.UpdateNoteThumbsUpRequest request,
        io.grpc.stub.StreamObserver<com.example.lib.UpdateNoteThumbsUpResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateNoteThumbsUpMethod(), responseObserver);
    }

    /**
     * <pre>
     * Deletes a note
     * </pre>
     */
    default void deleteNote(com.example.lib.DeleteNoteRequest request,
        io.grpc.stub.StreamObserver<com.example.lib.DeleteNoteResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteNoteMethod(), responseObserver);
    }

    /**
     * <pre>
     * Gets a single note by ID
     * </pre>
     */
    default void getNote(com.example.lib.GetNoteRequest request,
        io.grpc.stub.StreamObserver<com.example.lib.GetNoteResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetNoteMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service MyService.
   * <pre>
   * The note service definition.
   * </pre>
   */
  public static abstract class MyServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MyServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service MyService.
   * <pre>
   * The note service definition.
   * </pre>
   */
  public static final class MyServiceStub
      extends io.grpc.stub.AbstractAsyncStub<MyServiceStub> {
    private MyServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MyServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MyServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Creates a new note
     * </pre>
     */
    public void createNote(com.example.lib.CreateNoteRequest request,
        io.grpc.stub.StreamObserver<com.example.lib.CreateNoteResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateNoteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Lists all notes
     * </pre>
     */
    public void listNotes(com.example.lib.ListNotesRequest request,
        io.grpc.stub.StreamObserver<com.example.lib.ListNotesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListNotesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Updates a note's thumbs up count
     * </pre>
     */
    public void updateNoteThumbsUp(com.example.lib.UpdateNoteThumbsUpRequest request,
        io.grpc.stub.StreamObserver<com.example.lib.UpdateNoteThumbsUpResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateNoteThumbsUpMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deletes a note
     * </pre>
     */
    public void deleteNote(com.example.lib.DeleteNoteRequest request,
        io.grpc.stub.StreamObserver<com.example.lib.DeleteNoteResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteNoteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Gets a single note by ID
     * </pre>
     */
    public void getNote(com.example.lib.GetNoteRequest request,
        io.grpc.stub.StreamObserver<com.example.lib.GetNoteResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetNoteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service MyService.
   * <pre>
   * The note service definition.
   * </pre>
   */
  public static final class MyServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MyServiceBlockingStub> {
    private MyServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MyServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MyServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Creates a new note
     * </pre>
     */
    public com.example.lib.CreateNoteResponse createNote(com.example.lib.CreateNoteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateNoteMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Lists all notes
     * </pre>
     */
    public com.example.lib.ListNotesResponse listNotes(com.example.lib.ListNotesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListNotesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Updates a note's thumbs up count
     * </pre>
     */
    public com.example.lib.UpdateNoteThumbsUpResponse updateNoteThumbsUp(com.example.lib.UpdateNoteThumbsUpRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateNoteThumbsUpMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Deletes a note
     * </pre>
     */
    public com.example.lib.DeleteNoteResponse deleteNote(com.example.lib.DeleteNoteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteNoteMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Gets a single note by ID
     * </pre>
     */
    public com.example.lib.GetNoteResponse getNote(com.example.lib.GetNoteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetNoteMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service MyService.
   * <pre>
   * The note service definition.
   * </pre>
   */
  public static final class MyServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<MyServiceFutureStub> {
    private MyServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MyServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MyServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Creates a new note
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.lib.CreateNoteResponse> createNote(
        com.example.lib.CreateNoteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateNoteMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Lists all notes
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.lib.ListNotesResponse> listNotes(
        com.example.lib.ListNotesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListNotesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Updates a note's thumbs up count
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.lib.UpdateNoteThumbsUpResponse> updateNoteThumbsUp(
        com.example.lib.UpdateNoteThumbsUpRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateNoteThumbsUpMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Deletes a note
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.lib.DeleteNoteResponse> deleteNote(
        com.example.lib.DeleteNoteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteNoteMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Gets a single note by ID
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.lib.GetNoteResponse> getNote(
        com.example.lib.GetNoteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetNoteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_NOTE = 0;
  private static final int METHODID_LIST_NOTES = 1;
  private static final int METHODID_UPDATE_NOTE_THUMBS_UP = 2;
  private static final int METHODID_DELETE_NOTE = 3;
  private static final int METHODID_GET_NOTE = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_NOTE:
          serviceImpl.createNote((com.example.lib.CreateNoteRequest) request,
              (io.grpc.stub.StreamObserver<com.example.lib.CreateNoteResponse>) responseObserver);
          break;
        case METHODID_LIST_NOTES:
          serviceImpl.listNotes((com.example.lib.ListNotesRequest) request,
              (io.grpc.stub.StreamObserver<com.example.lib.ListNotesResponse>) responseObserver);
          break;
        case METHODID_UPDATE_NOTE_THUMBS_UP:
          serviceImpl.updateNoteThumbsUp((com.example.lib.UpdateNoteThumbsUpRequest) request,
              (io.grpc.stub.StreamObserver<com.example.lib.UpdateNoteThumbsUpResponse>) responseObserver);
          break;
        case METHODID_DELETE_NOTE:
          serviceImpl.deleteNote((com.example.lib.DeleteNoteRequest) request,
              (io.grpc.stub.StreamObserver<com.example.lib.DeleteNoteResponse>) responseObserver);
          break;
        case METHODID_GET_NOTE:
          serviceImpl.getNote((com.example.lib.GetNoteRequest) request,
              (io.grpc.stub.StreamObserver<com.example.lib.GetNoteResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateNoteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.lib.CreateNoteRequest,
              com.example.lib.CreateNoteResponse>(
                service, METHODID_CREATE_NOTE)))
        .addMethod(
          getListNotesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.lib.ListNotesRequest,
              com.example.lib.ListNotesResponse>(
                service, METHODID_LIST_NOTES)))
        .addMethod(
          getUpdateNoteThumbsUpMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.lib.UpdateNoteThumbsUpRequest,
              com.example.lib.UpdateNoteThumbsUpResponse>(
                service, METHODID_UPDATE_NOTE_THUMBS_UP)))
        .addMethod(
          getDeleteNoteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.lib.DeleteNoteRequest,
              com.example.lib.DeleteNoteResponse>(
                service, METHODID_DELETE_NOTE)))
        .addMethod(
          getGetNoteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.lib.GetNoteRequest,
              com.example.lib.GetNoteResponse>(
                service, METHODID_GET_NOTE)))
        .build();
  }

  private static abstract class MyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.lib.NoteServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MyService");
    }
  }

  private static final class MyServiceFileDescriptorSupplier
      extends MyServiceBaseDescriptorSupplier {
    MyServiceFileDescriptorSupplier() {}
  }

  private static final class MyServiceMethodDescriptorSupplier
      extends MyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    MyServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MyServiceFileDescriptorSupplier())
              .addMethod(getCreateNoteMethod())
              .addMethod(getListNotesMethod())
              .addMethod(getUpdateNoteThumbsUpMethod())
              .addMethod(getDeleteNoteMethod())
              .addMethod(getGetNoteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
