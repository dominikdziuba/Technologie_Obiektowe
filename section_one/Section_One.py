from section_one.Group_One import *
from section_one.Group_Three import *
from section_one.Group_Two import *
from section_one.Group_Four import *
from section_one.Group_Five import *
from section_one.Group_Six import *
from section_one.Group_Seven import *
from section_one.Rest_Groups import *


class Section_One():
    def __init__(self):
        self.group_one = Group_One()
        self.group_two = Group_Two()
        self.group_three = Group_Three()
        self.group_four = Group_Four()
        self.group_five = Group_Five()
        self.group_six = Group_Six()
        self.group_seven = Group_Seven()
        self.rest_groups = Rest_Groups()