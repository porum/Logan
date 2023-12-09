# Logan Cmdline Parser

```shell
Usage: <main class> [options] <input file>
  Options:
    --help

    -legacy
      use legacy protocol
      Default: false
    -out
      output file

```

1. 项目使用 idea 打包；具体操作可参考：https://blog.csdn.net/yzc775210/article/details/131541168
2. 打包出来的 jar 包需要手动删除 META-INF/ 下的 *.DSA *.SF；windows 下使用 WinRAR 打开直接删除