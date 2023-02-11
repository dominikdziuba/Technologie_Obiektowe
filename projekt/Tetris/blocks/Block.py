import pygame
import copy
from Tetris.Data import *

class Block():
    def __init__(self, figures, x, y, board, color, rotation):
        self.x = x
        self.y = y
        self.color = color

        self.board = board

        self.rotation = rotation

        self.newX = 0
        self.newY = 0
        self.rotation_angle = 0

        self.figures = []
        for fr in figures:
            block_x = fr[0] * block_width + x
            block_y = fr[1] * block_height + y
            block = pygame.Rect(block_x, block_y, block_width, block_height)
            self.figures.append(block)

    def create(self):
        for fr in self.figures:
            pygame.draw.rect(self.board, self.color, fr)
            pygame.draw.rect(self.board, black, fr, block_margin)

    def remove_blocks(self, y):
        new_conf = []
        for br in range(len(self.figures)):
            temp_conf = self.figures[br]
            if temp_conf.y < y:
                new_conf.append(temp_conf)
                temp_conf.move_ip(0, block_height)

            elif temp_conf.y > y:
                new_conf.append(temp_conf)

        self.figures = new_conf

    def in_blocks_list(self):
        if len(self.figures) > 0:
            return True
        else:
            return False


    def copy_data(self):
        self.copy_blocks = copy.deepcopy(self.figures)
        self.copy_x = self.x
        self.copy_y = self.y
        self.copy_rotation_angle = self.rotation_angle

    def backup_data(self):
        self.figures = self.copy_blocks
        self.x = self.copy_x
        self.y = self.copy_y
        self.rotation_angle = self.copy_rotation_angle

    def collision_checking(self, blocks_list):

        for blocks in blocks_list:
            collision_list = blocks.collidelistall(self.figures)
            if len(collision_list):
                return True
        return False