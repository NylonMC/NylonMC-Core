from rubicon.java import JavaClass
import io
import sys
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

print("test")
print("test")