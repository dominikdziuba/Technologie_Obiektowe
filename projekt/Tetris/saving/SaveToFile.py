import os

class ScoreSingleton:
    __instance = None

    @staticmethod
    def getInstance():
        """ Static access method. """
        if ScoreSingleton.__instance == None:
            ScoreSingleton()
        return ScoreSingleton.__instance

    def __init__(self):
        """ Virtually private constructor. """
        if ScoreSingleton.__instance != None:
            raise Exception("This class is a singleton!")
        else:
            ScoreSingleton.__instance = self
            self.scores = {}
            self.file_name = "scores.txt"
            if not os.path.isfile(self.file_name):
                open(self.file_name, 'w').close()

    def save_score(self, name, score):
        self.scores[name] = score
        with open(self.file_name, 'a') as file:
            for player, scr in self.scores.items():
                file.write("{},{}\n".format(player, scr))
