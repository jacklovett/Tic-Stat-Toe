export type StatData = {
  gameCount: number
  xWinnerCount: number
  oWinnerCount: number
  drawCount: number
  avgGameTime: string
  maxGameTime: string
  minGameTime: string
  startPositioinsX: string
  startPositionsO: string
  winningStartPositionsX: string
  winningStartPositionsO: string
}

type StatDataKey = keyof StatData

export type StatItem = {
  key: string
  label: string
  value?: string | number
}

export const statItems: StatItem[] = [
  {
    key: 'gameCount',
    label: 'Total number of games played',
  },
  {
    key: 'winnerCountX',
    label: 'How many times X won',
  },
  {
    key: 'winnerCountO',
    label: 'How many times O won',
  },
  {
    key: 'drawCount',
    label: 'Total number of draws',
  },
  {
    key: 'avgGameTime',
    label: 'Average game time',
  },
  {
    key: 'maxGameTime',
    label: 'Longest game time',
  },
  {
    key: 'minGameTime',
    label: 'Shortest game time',
  },
  {
    key: 'startPositionsX',
    label: 'Most popular start position (X)',
  },
  {
    key: 'startPositionsO',
    label: 'Most popular start position (O)',
  },
  {
    key: 'winningStartPositionsX',
    label: 'Most popular winning start position (X)',
  },
  {
    key: 'winningStartPositionsO',
    label: 'Most popular winning start position (O)',
  },
]

export const dataTransformer = (statData: StatData): StatItem[] => {
  return statItems.map((statItem: StatItem) => {
    return {
      ...statItem,
      value: statData[statItem.key as StatDataKey] ?? '-',
    } as StatItem
  })
}
