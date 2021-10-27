import React, { useEffect, useState } from 'react'
import { RouterProps } from 'react-router-dom'
import { Stack, Text } from '@fluentui/react'

import {
  calculateWinner,
  GameHistory,
  getPlayer,
  getStatus,
  initialGameHistoryState,
  saveGameData,
} from '../services/gameService'

import { Board } from '../components/Board'
import { Header } from '../components/Header'
import { Page } from '../components/Page'
import { ButtonIcon } from '../components/ButtonIcon'

const styles = {
  gameInfo: {
    root: {
      minWidth: 160,
      height: 80,
      color: '#ffffff',
      padding: 20,
      fontSize: 24,
    },
  },
}

export const Game = (props: RouterProps) => {
  const { history } = props

  const [start, setStart] = useState<Date>(new Date())
  const [gameHistory, setGameHistory] = useState<GameHistory>(
    initialGameHistoryState,
  )

  const { turns, currentBoardState, stepNumber, winner, isX } = gameHistory

  const player = getPlayer(isX)

  useEffect(() => {
    if (winner) {
      saveGameData({
        turns: turns.slice(1, turns.length), // Remove empty initial board state
        winner,
        start,
        end: new Date(),
      })
    }
  }, [turns, winner, start])

  const selectSquare = (index: number, position: string) => {
    // First box has been selected, set start time
    if (stepNumber === 0) {
      setStart(new Date())
    }

    const newStateHistory = turns.slice(0, stepNumber + 1)
    const nextTurn = newStateHistory[turns.length - 1]
    const squares = nextTurn.boardHistory.slice()

    if (winner || squares[index]) {
      return
    }

    squares[index] = player

    setGameHistory({
      turns: turns.concat([
        { boardHistory: squares, selectedSquare: position },
      ]),
      currentBoardState: squares,
      stepNumber: stepNumber + 1,
      winner: calculateWinner(squares, stepNumber),
      isX: !isX,
    })
  }

  return (
    <Page
      header={
        <Header
          navButtonProps={{
            text: 'Show Statistics',
            iconName: 'chart',
            onClick: () => history.push('/stats'),
          }}
        />
      }
      body={
        <Stack
          verticalAlign="center"
          horizontalAlign="center"
          grow
          verticalFill
        >
          <Stack
            horizontal
            horizontalAlign="center"
            wrap
            tokens={{ childrenGap: 20 }}
          >
            <Board
              squares={currentBoardState}
              selectSquare={(i: number, position: string) =>
                selectSquare(i, position)
              }
            />
            <Stack
              styles={styles.gameInfo}
              horizontalAlign="center"
              tokens={{ childrenGap: 20 }}
            >
              <Text styles={{ root: { color: '#ffffff', fontSize: 18 } }}>
                {getStatus(player, winner)}
              </Text>
              {stepNumber && (
                <ButtonIcon
                  text="Reset"
                  iconName="Refresh"
                  onClick={() => setGameHistory(initialGameHistoryState)}
                />
              )}
            </Stack>
          </Stack>
        </Stack>
      }
    />
  )
}
