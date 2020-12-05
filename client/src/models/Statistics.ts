export type StatData = {
  gameCount: number
  xWinnerCount: number
  oWinnerCount: number
  drawCount: number
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
]

export const dataTransformer = (statData: StatData): StatItem[] => {
  return statItems.map((statItem: StatItem) => {
    return {
      ...statItem,
      value: statData[statItem.key as StatDataKey] ?? '-',
    } as StatItem
  })
}
