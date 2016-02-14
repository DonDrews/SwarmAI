import sys
class Unit:
        def __init__(self, i, xpos, ypos, directions):
                self.id = int(i)
                self.x = float(xpos)
                self.y = float(ypos)
                self.d = float(directions)
def move(spd):
        print 'mv ' + str(spd)
        return
def turn(rad):
        print 'tr ' + str(rad)
        return
def setDir(dir):
        print 'sd ' + str(dir)
        return
unitAmount = (len(sys.argv) - 1) / 4
units = []
me = None
for i in range(0, unitAmount):
        if i == 0:
                me = Unit(sys.argv[1 + (4 * i)], sys.argv[2 + (4 * i)], sys.argv[3 + (4 * i)], sys.argv[4 + (4 * i)])
        else:
                u = Unit(sys.argv[1 + (4 * i)], sys.argv[2 + (4 * i)], sys.argv[3 + (4 * i)], sys.argv[4 + (4 * i)])
                units.append(u)
import math
#find nearest unit
candidate = None
dist = 100000
for unit in units:
        if abs(unit.x - me.x) + abs(unit.y - me.y) < dist:
                candidate = unit
                dist = abs(unit.x - me.x) + abs(unit.y - me.y)

#move based on distance
if dist < 5:
        #move away
        reverseTheta = math.atan2(candidate.x - me.x, candidate.y - me.y)
        setDir(reverseTheta)
        move(1)
elif dist < 20:
        #move with
        setDir(candidate.d)
        move(1)
else:
        #move toward
        theta = math.atan2(me.x - candidate.x, me.y - candidate.y)
        setDir(theta)
        move(1)




print 'exit'