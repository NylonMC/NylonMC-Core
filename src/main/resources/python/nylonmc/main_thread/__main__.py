from rubicon.java import JavaClass
import io
import sys
import traceback
_log = JavaClass("org/apache/logging/log4j/LogManager").getLogger("Python")

class _StdoutWrapper(io.StringIO):
    def flush(self):
        for line in self.getvalue().split("\n"):
            if line != "":
                _log.info(line)
sys.stdout = _StdoutWrapper()

class _StderrWrapper(io.StringIO):
    def flush(self):
        for line in self.getvalue().split("\n"):
            if line != "":
                _log.error(line)
sys.stderr = _StderrWrapper()

def _full_stack():
    exc = sys.exc_info()[0]
    if exc is not None:
        f = sys.exc_info()[-1].tb_frame.f_back
        stack = traceback.extract_stack(f)
    else:
        stack = traceback.extract_stack()[:-1]  # last one would be full_stack()
    trc = 'Traceback (most recent call last):\n'
    stackstr = trc + ''.join(traceback.format_list(stack))
    if exc is not None:
        stackstr += '  ' + traceback.format_exc().lstrip(trc)
    return stackstr

_PythonThread = JavaClass("io/github/nylonmc/core/PythonThread")

while True:
    try:
        exec(_PythonThread.getCodeInternal())
    except Exception as e:
        _log.error("Error Running User Python Code")
        _log.error(str(e))
        _log.error(_full_stack())
    _PythonThread.getjThread().resume()
    _PythonThread.getPyThread().suspend()