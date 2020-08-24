git submodule foreach git pull origin master
rm ./src/main/java/org/beeware/rubicon/Python.java
mkdir -p ./src/main/java/org/beeware/rubicon/
cp ./rubicon-java/org/beeware/rubicon/Python.java ./src/main/java/org/beeware/rubicon/Python.java
rm ./src/main/java/org/beeware/rubicon/PythonInstance.java
mkdir -p ./src/main/java/org/beeware/rubicon/
cp ./rubicon-java/org/beeware/rubicon/PythonInstance.java ./src/main/java/org/beeware/rubicon/PythonInstance.java
rm -r ./src/main/resources/python/rubicon
mkdir -p ./src/main/resources/python/
cp -r ./rubicon-java/rubicon ./src/main/resources/python/rubicon