#bin/bash

#Init Variable values
export MAVEN_VERSION=3
export MAVEN_FULL_VERSION=3.9.1
export BASE_URL=https://dlcdn.apache.org/maven/maven-$MAVEN_VERSION/$MAVEN_FULL_VERSION/binaries/apache-maven-$MAVEN_FULL_VERSION-bin.tar.gz
export MAVEN_FOLDER_NAME=apache-maven-$MAVEN_FULL_VERSION
export MAVEN_COMMAND=/$MAVEN_FOLDER_NAME/bin/mvn

#Maven health check
$MAVEN_COMMAND -v
if [ "$?" -ne 0 ]; then
    #Download Maven tarball
    echo Dowloading maven from $BASE_URL...
    curl -o /$MAVEN_FOLDER_NAME.tar.gz $BASE_URL
    echo Download complete!

    #Extracting tarball
    echo Extracting..
    tar -xvf /$MAVEN_FOLDER_NAME.tar.gz -C /
    rm /$MAVEN_FOLDER_NAME.tar.gz
    echo Extraction complete!
fi


#Build jar file
$MAVEN_COMMAND clean package

#Start Program
java -jar /app/target/logging-service-0.0.1-SNAPSHOT.jar