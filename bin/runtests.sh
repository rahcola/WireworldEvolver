#!/bin/bash

cd ../build
echo "CellTest"
java -cp "../junit-4.7.jar:." "wireworldevolver.test.CellTest"
echo "BoardFileManagerTest"
java -cp "../junit-4.7.jar:." "wireworldevolver.test.BoardFileManagerTest"

