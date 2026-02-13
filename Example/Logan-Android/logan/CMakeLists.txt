cmake_minimum_required(VERSION 3.22.1)

SET (CMAKE_C_FLAGS_DEBUG          "-g")
SET (CMAKE_C_FLAGS_RELEASE        "-O2 -DNDEBUG")

set(EXTERN_DIR ../../../Logan/Clogan)

add_subdirectory(${EXTERN_DIR} clogan.out)

include_directories(${EXTERN_DIR})

link_directories(clogan.out)

add_library(logan SHARED src/main/jni/clogan_protocol.c)
target_link_libraries(logan z clogan)

target_link_options(logan PRIVATE "-Wl,-z,max-page-size=16384")
