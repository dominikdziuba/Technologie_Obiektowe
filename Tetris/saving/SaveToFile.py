import os

class SaveToFile:
    __instance = None

    @staticmethod
    def getInstance():
        if SaveToFile.__instance == None:
            SaveToFile()
        return SaveToFile.__instance

    def __init__(self):
        if SaveToFile.__instance != None:
            raise Exception("This class is a singleton!")
        else:
            SaveToFile.__instance = self
            self.scores = {}
            self.file_name = "scores.txt"
            if not os.path.isfile(self.file_name):
                open(self.file_name, 'w').close()

    def save_score(self, name, score):
        self.scores[name] = score
        with open(self.file_name, 'a') as file:
            for player, scr in self.scores.items():
                file.write("{},{}\n".format(player, scr))
