#!/bin/bash

for i in `ls Tests/graphs`
do
    dot -Tpng -O "Tests/graphs/"$i
done