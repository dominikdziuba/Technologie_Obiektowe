import pygame
import math
import copy
from Tetris.Data import *

class Block(object):
    def __init__(self,shapes, x, y, board, color, rotation):
        self.x = x
        self.y = y
        self.color = color

        self.board = board

        self.rotation = rotation

        self.newX = 0
        self.newY = 0
        self.rotation_angle = 0

        self.shapes = []
        for bl in shapes:
            block_x = bl[0] * block_width + x
            block_y = bl[1] * block_height + y
            block = pygame.Rect(block_x, block_y, block_width, block_height)
            self.shapes.append(block)

    def create(self):
        for bl in self.shapes:
            pygame.draw.rect(self.board, self.color, bl)
            pygame.draw.rect(self.board, black, bl, block_margin)





    def remove_blocks(self, y):
        new_conf = []
        for bl in range(len(self.shapes)):
            temp_conf = self.shapes[bl]
            if temp_conf.y < y:
                new_conf.append(temp_conf)
                temp_conf.move_ip(0, block_height)

            elif temp_conf.y > y:
                new_conf.append(temp_conf)

        self.shapes = new_conf

    def in_blocks_list(self):
        if len(self.shapes) > 0:
            return True
        else:
            return False


    def copy_data(self):
        self.copy_blocks = copy.deepcopy(self.shapes)
        self.copy_x = self.x
        self.copy_y = self.y
        self.copy_rotation_angle = self.rotation_angle

    def backup_data(self):
        self.shapes = self.copy_blocks
        self.x = self.copy_x
        self.y = self.copy_y
        self.rotation_angle = self.copy_rotation_angle

    def collision_checking(self, rect_list):

        for blk in rect_list:
            collist = blk.collidelistall(self.shapes)
            if len(collist):
                return True
        return False