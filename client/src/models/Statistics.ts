export type StatData = {
  gameCount: number
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
]

export const dataTransformer = (statData: StatData): StatItem[] => {
  return statItems.map((statItem: StatItem) => {
    return {
      ...statItem,
      value: statData[statItem.key as StatDataKey],
    } as StatItem
  })
}
