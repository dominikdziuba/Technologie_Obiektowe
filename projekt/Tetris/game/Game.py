import pygame

import random
import math
from Tetris.blocks import Block
from Tetris.Data import *
from Tetris.saving import SaveToFile
from Tetris.blocks import Movement

class Game():

    def __init__(self, board_width, board_height):

        self.sizeX = board_width * block_width + 2 * frame_thickness + frame_margin
        self.sizeY = board_height * block_height + 2 * frame_thickness + frame_margin

        self.top_line = pygame.Rect(0, scoreboard_height, self.sizeX, frame_thickness)
        self.right_line = pygame.Rect(self.sizeX - frame_thickness, scoreboard_height, frame_thickness, self.sizeY)
        self.bottom_line = pygame.Rect(0, self.sizeY - frame_thickness, self.sizeX, frame_thickness)
        self.left_line = pygame.Rect(0, scoreboard_height, frame_thickness, self.sizeY)

        self.blocks_list = []

        self.beginning_x = math.ceil(self.sizeX / 2.0)
        self.beginning_y = scoreboard_height + frame_thickness + frame_margin

        self.figures = (
            ([[0, 0], [1, 0], [2, 0], [3, 0]], red, True),
            ([[0, 0], [1, 0], [0, 1], [-1, 1]], green, True),
            ([[0, 0], [1, 0], [2, 0], [2, 1]], blue, True),
            ([[0, 0], [0, 1], [1, 0], [1, 1]], orange, False),
            ([[-1, 0], [0, 0], [0, 1], [1, 1]], yellow, True),
            ([[0, 0], [1, 0], [2, 0], [1, 1]], purple, True),
            ([[0, 0], [1, 0], [2, 0], [0, 1]], cyan, True),
        )
        if board_width % 2 == 0:
            self.number_of_blocks_in_line = board_width
        else:
            self.number_of_blocks_in_line = board_width - 1
        self.blocks_in_pile = board_height

        self.score = 0
        self.saving = SaveToFile.getInstance()

        self.speed = 1

        self.score_level = next_level

    def create_board(self):

        self.game_board.fill(black)
        self.create_frames()
        for block in self.blocks_list:
            block.create()
        pygame.display.flip()

    def create_frames(self):

        pygame.draw.rect(self.game_board, white, self.top_line)
        pygame.draw.rect(self.game_board, white, self.right_line)
        pygame.draw.rect(self.game_board, white, self.bottom_line)
        pygame.draw.rect(self.game_board, white, self.left_line)
        self.display_info()

    def save_score (self):
        player_name = ""
        self.saving.save_score(player_name, self.score)
        self.ended = True


    def display_info(self):

        caption = ["SCORE: {0}   SPEED: {1}x".format(self.score, self.speed)]
        self.display_text(caption, scoreboard_margin, scoreboard_margin)

    def input_player_name(self):
        input_box = pygame.Rect(50, 50, 140, 32)
        frame_color = pygame.Color('lightskyblue3')
        color = frame_color
        active = True
        text = ''
        ended = False
        while not ended:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    ended = True
                if event.type == pygame.KEYDOWN:
                    if active:
                        if event.unicode == '\r':
                            ended = True
                        elif event.unicode == '\x7f':
                            text = text[:-1]
                        else:
                            text += event.unicode

            self.game_board.fill((30, 30, 30))
            player_name_text = self.my_font.render("Podaj nazwe gracza", True, frame_color)
            self.game_board.blit(player_name_text, (50, 20))
            txt_surface = self.my_font.render(text, True, color)
            width = max(200, txt_surface.get_width() + 10)
            input_box.w = width
            pygame.draw.rect(self.game_board, color, input_box, 2)
            self.game_board.blit(txt_surface, (input_box.x + 5, input_box.y + 5))
            pygame.display.flip()

        return text

    def endgame_handle(self):
        player_name=self.input_player_name()
        if player_name == "":
            pass
        else:
            self.saving.save_score(player_name, self.score)

        pygame.display.flip()

        while True:
            for ev in pygame.event.get():
                if ev.type == pygame.QUIT or (ev.type == pygame.KEYDOWN and ev.unicode == 'q'):
                    return

    def display_text(self, string_list, x_pos, y_pos):
        previous_height = 0
        for text in string_list:
            text_width, text_height = self.my_font.size(text)
            text_surface = self.my_font.render(text, False, (255, 255, 255))
            self.game_board.blit(text_surface, (x_pos, y_pos + previous_height))
            previous_height += text_height

    def display_score(self, string_list):

        max_width = max([tmp[0] for tmp in map(self.my_font.size, string_list)])
        self.display_text(string_list, self.sizeX / 2 - max_width / 2, self.sizeY / 2)

    def keyboard_action(self):

        for e in pygame.event.get():

            if e.type == pygame.QUIT or (e.type == pygame.KEYDOWN and e.unicode == 'q'):
                self.endgame_handle()
                self.ended = True
            if e.type == pygame.KEYDOWN:
                if e.key == pygame.K_DOWN:
                    self.movement.move(0, block_height)
                if e.key == pygame.K_LEFT:
                    self.movement.move(-block_width, 0)
                if e.key == pygame.K_RIGHT:
                    self.movement.move(block_width, 0)
                if e.key == pygame.K_SPACE:
                    self.movement.rotate_angle()

            if e.type == move_event:
                self.movement.move(0, block_height)

    def speed_up(self):
        speed = math.floor(move_tempo / self.speed)
        speed = max(1, speed)
        pygame.time.set_timer(move_event, speed)

    def collisions_handle(self):

        for block in self.blocks_list:
            if block == self.moving_block:
                continue

            if (block.collision_checking(self.moving_block.figures)):
                return True
        return False

    def rules(self):

        self.moving_block.copy_data()
        self.keyboard_action()
        down_board = self.moving_block.collision_checking([self.bottom_line])
        any_border = self.moving_block.collision_checking([self.left_line, self.top_line, self.right_line])
        other_block = self.collisions_handle()

        if down_board or any_border or other_block:
            self.moving_block.backup_data()
        self.moving_block.copy_data()
        self.movement.move(0, block_height)
        can_go_down = not self.collisions_handle()
        self.moving_block.backup_data()
        if not can_go_down and (self.beginning_x == self.moving_block.x and self.beginning_y == self.moving_block.y):
            self.game_over = True
        if down_board or not can_go_down:
            self.new_block = True
            self.check_line()

    def check_line(self):
        for shape in self.moving_block.figures:
            temp_height = shape.y
            blocks_in_current_line = self.counter_of_blocks_in_line(temp_height)

            if blocks_in_current_line != self.number_of_blocks_in_line:
                continue

            self.delete_filled_line(temp_height)

            self.score += self.number_of_blocks_in_line * point_for_block
            if self.score > self.score_level:
                self.score_level *= next_level_ratio
                self.speed *= speed_increase
                self.speed_up()

    def delete_filled_line(self, y):
        for block in self.blocks_list:
            block.remove_blocks(y)
        self.blocks_list = [block for block in self.blocks_list if block.in_blocks_list()]

    def counter_of_blocks_in_line(self, y):

        blocks_in_current_line = 0
        for block in self.blocks_list:
            for shape in block.figures:
                blocks_in_current_line += (1 if y == shape.y else 0)
        return blocks_in_current_line

    def get_block(self):
        if self.new_block:
            temp_block = random.randint(0, len(self.figures) - 1)
            fr = self.figures[temp_block]
            self.moving_block = Block(fr[0], self.beginning_x, self.beginning_y, self.game_board, fr[1], fr[2])
            self.movement = Movement(self.moving_block)
            self.blocks_list.append(self.moving_block)
            self.new_block = False

    def run(self):
        pygame.init()
        pygame.font.init()
        self.my_font = pygame.font.SysFont(pygame.font.get_default_font(), font_size)
        self.game_board = pygame.display.set_mode((self.sizeX, self.sizeY))
        pygame.display.set_caption("Tetris")
        self.speed_up()
        self.ended = False
        self.game_over = False
        self.new_block = True
        self.display_info()
        while not (self.ended) and not (self.game_over):
            self.get_block()
            self.rules()
            self.create_board()
        if self.game_over:
            self.endgame_handle()
        pygame.font.quit()
        pygame.display.quit()

