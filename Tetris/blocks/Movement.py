import math
from Tetris.Data import  *


class Movement():
    def __init__(self, block):
        self.block = block

    def rotate(self, x, y):
        angle_radians = self.block.rotation_angle * (math.pi / 180.0)
        new_x = x * math.cos(angle_radians) - y * math.sin(angle_radians)
        new_y = y * math.cos(angle_radians) + x * math.sin(angle_radians)
        return (new_x, new_y)

    def move(self, x, y):

        self.block.newX += x
        self.block.newY += y
        self.update_coordinates()



    def rotate_angle(self):
        if self.block.rotation:
            self.block.rotation_angle = 90
            self.update_coordinates()

    def update_coordinates(self):
        for bl in self.block.figures:
            old_x = (bl.x - self.block.x) / block_width
            old_y = (bl.y - self.block.y) / block_height
            rx, ry = self.rotate(old_x, old_y)
            new_x = rx * block_width + self.block.x + self.block.newX
            new_y = ry * block_height + self.block.y + self.block.newY
            new_coord_x = new_x - bl.x
            new_coord_y = new_y - bl.y
            bl.move_ip(new_coord_x, new_coord_y)
        self.block.x += self.block.newX
        self.block.y += self.block.newY
        self.block.newX = 0
        self.block.newY = 0
        self.block.rotation_angle = 0
