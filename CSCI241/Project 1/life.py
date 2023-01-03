import sys, threading, time
from PyQt5.QtGui import QPainter, QColor, QFont, QPen, QBrush
from PyQt5.QtCore import Qt
from PyQt5.QtWidgets import QWidget, QApplication, QPushButton

# if you change CELL_COUNT, be sure that initial
# patterns in constructor are still valid
CELL_COUNT = 30
CELL_SIZE = 40
GRID_ORIGINX = 50
GRID_ORIGINY = 50

class GameOfLife(QWidget):

  def __init__(self):
    super().__init__()
    self.__current_gen = [[0 for c in range(CELL_COUNT)] for r in range(CELL_COUNT)]
    self.__next_gen = [[0 for c in range(CELL_COUNT)] for r in range(CELL_COUNT)]

    # initialize a glider
    self.__current_gen[5][15] = 1
    self.__current_gen[4][14] = 1
    self.__current_gen[4][13] = 1
    self.__current_gen[5][13] = 1
    self.__current_gen[6][13] = 1

    # initialize a block
    self.__current_gen[17][4] = 1
    self.__current_gen[17][5] = 1
    self.__current_gen[16][4] = 1
    self.__current_gen[16][5] = 1

    # initialize a blinker
    self.__current_gen[14][14] = 1
    self.__current_gen[14][15] = 1
    self.__current_gen[14][16] = 1
    self.__running = False
    self.__run_thread = None
    self.setWindowTitle('GameOfLife')
    toggle_button = QPushButton('Start/Stop', self)
    self.setGeometry(300, 300, 2 * GRID_ORIGINX + CELL_SIZE * CELL_COUNT, \
        3 * GRID_ORIGINY + CELL_SIZE * CELL_COUNT + toggle_button.sizeHint().height())
    toggle_button.move(GRID_ORIGINX, 2 * GRID_ORIGINY + (CELL_COUNT * CELL_SIZE))
    toggle_button.clicked.connect(self.__toggle)
    self.show()


  def paintEvent(self, event):
    qp = QPainter()

    blackPen = QPen(QBrush(Qt.black), 1)
    qp.begin(self)

    # clear the background
    qp.fillRect(event.rect(), Qt.white)

    qp.setPen(blackPen)

    # draw each cell
    for r in range(len(self.__current_gen)):
      for c in range(len(self.__current_gen[r])):
        qp.drawRect(GRID_ORIGINX + c * CELL_SIZE, GRID_ORIGINY + r * CELL_SIZE, CELL_SIZE, CELL_SIZE)
        if self.__current_gen[r][c] == 0:
          fillColor = Qt.white
        elif 1 <= self.__current_gen[r][c] < 6:
          fillColor = Qt.darkBlue
        elif 6 <= self.__current_gen[r][c] < 11:
          fillColor = Qt.darkGreen
        elif 11 <= self.__current_gen[r][c] < 16:
          fillColor = Qt.darkYellow
        elif self.__current_gen[r][c] >= 16:
          fillColor = Qt.darkRed
        qp.fillRect(GRID_ORIGINX + c * CELL_SIZE + 3, GRID_ORIGINY + r * CELL_SIZE + 3, CELL_SIZE - 5, CELL_SIZE - 5, fillColor)
    qp.end()

  def mousePressEvent(self, event):
    row = (event.y() - GRID_ORIGINY) // CELL_SIZE
    col = (event.x() - GRID_ORIGINX) // CELL_SIZE
    if 0 <= row < CELL_COUNT and 0 <= col < CELL_COUNT:
      if self.__current_gen[row][col] == 0:
        self.__current_gen[row][col] = 1
      else:
        self.__current_gen[row][col] = 0
    self.update()

  def closeEvent(self, event):
    if self.__running is True:
      self.__running = False
      self.__run_thread.join()

  def __count_live_neighbors(self, r, c):
    count = 0
    # only have to mod addition because Python
    # automatically wraps negative indices
    if self.__current_gen[r-1][c-1] > 0:
      count = count + 1
    if self.__current_gen[r-1][c] > 0:
      count = count + 1
    if self.__current_gen[r-1][(c+1)%CELL_COUNT] > 0:
      count = count + 1
    if self.__current_gen[r][c-1] > 0:
      count = count + 1
    if self.__current_gen[r][(c+1)%CELL_COUNT] > 0:
      count = count + 1
    if self.__current_gen[(r+1)%CELL_COUNT][c-1] > 0:
      count = count + 1
    if self.__current_gen[(r+1)%CELL_COUNT][c] > 0:
      count = count + 1
    if self.__current_gen[(r+1)%CELL_COUNT][(c+1)%CELL_COUNT] > 0:
      count = count + 1
    return count

  def __compute_next_generation(self):
    for r in range(len(self.__current_gen)):
      for c in range(len(self.__current_gen[r])):
        neighbor_count = self.__count_live_neighbors(r, c)
        if self.__current_gen[r][c] == 0 and neighbor_count == 3:
          self.__next_gen[r][c] = 1
        elif self.__current_gen[r][c] > 0 and (neighbor_count == 2 or neighbor_count == 3):
          self.__next_gen[r][c] = self.__current_gen[r][c] + 1
        else:
          self.__next_gen[r][c] = 0
    temp = self.__current_gen
    self.__current_gen = self.__next_gen
    self.__next_gen = temp

  def __cycle_generations(self):
    while self.__running is True:
      self.__compute_next_generation()
      self.update()
      time.sleep(0.2)

  def __toggle(self):
    if not self.__running:
      self.__running = True
      self.__run_thread = threading.Thread(target=self.__cycle_generations)
      self.__run_thread.start()
    else:
      self.__running = False
      self.__run_thread.join()

if __name__ == '__main__':
  app = QApplication(sys.argv)
  ex = GameOfLife()
  sys.exit(app.exec_())
