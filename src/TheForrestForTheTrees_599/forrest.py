#!/usr/bin/env python3

import sys
import queue as q
import random as r
import time

def main():
    numTests = int(sys.stdin.readline())

    for i in range(numTests):
        used = [False] * 26
        treeCount = 0
        pointCount = 0
        while(True): 
            line = sys.stdin.readline()
            
            if '*' in line:
                break

            char1 = ord(line[1]) - 65 # ord('A') = 65
            char2 = ord(line[3]) - 65

            if used[char1] and used[char2]:
                treeCount -= 1
            elif used[char1] or used[char2]:
                pointCount += 1
            else:
                treeCount += 1
                pointCount += 2
            
            used[char1] = used[char2] = True

        nNodes = len(sys.stdin.readline())
        nNodes -= nNodes // 2

        print('There are {} tree(s) and {} acorn(s).'.format(treeCount, nNodes - pointCount))

def forestGenerator():
    testCases = 100000#r.randint(1, 10000)
    print(testCases)
    everyNode = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
    for i in range(testCases):
        file = open('Tests/graphs/g{}.dot'.format(i), 'w')
        file.write('graph G{} {{\n'.format(i))
        nNodes = r.randint(1, 26)
        nArcs = r.randint(0, nNodes * (nNodes - 1) / 2) #Triangular matrix without diagonal
        nodesToUse = r.sample(everyNode, nNodes)
        arcs = set()
        appearingNodes = []
        while(len(arcs) != nArcs):
            source = r.choice(nodesToUse)
            dest = r.choice(nodesToUse)
            if (source != dest):
                arc = '({},{})'.format(source, dest)
                reverseArc = '({},{})'.format(dest, source)
                if arc not in arcs and reverseArc not in arcs:
                    if (source not in appearingNodes):
                        appearingNodes.append(source)
                    if (dest not in appearingNodes):
                        appearingNodes.append(dest)
                    print(arc)
                    arcs.add(arc)
                    file.write('    {} -- {};\n'.format(source, dest))

        for j in nodesToUse:
            if j not in appearingNodes:
                file.write('    {};\n'.format(j))

        print('*********')
        print(','.join(nodesToUse))
        file.write('}\n')
        file.close()

# if len(sys.argv) == 1:
#     start = time.time()
#     main()
#     end = time.time()
#     print("Algorithm took {} seconds.".format(end - start))
# elif sys.argv[1] == 'generate':
#     forestGenerator()

main()