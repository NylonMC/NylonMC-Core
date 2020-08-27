from rubicon.java import JavaClass
_log = JavaClass("org/apache/logging/log4j/LogManager").getLogger("Yes")

def main():
    _log.info("Test")