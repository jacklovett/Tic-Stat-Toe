import moment from 'moment'
import { http } from './httpService'

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

const dataTransformer = (statData: StatData): StatItem[] => {
  return statItems.map((statItem: StatItem) => {
    const key = statItem.key as StatDataKey
    const valueString = `${statData[key]}`
    return {
      ...statItem,
      value: formatData(key, valueString),
    } as StatItem
  })
}

const formatData = (key: string, value: string): string => {
  return key.includes('Time') ? formatTime(value) : value ?? '-'
}

const formatTime = (value: string): string => {
  const duration = moment.duration(value)
  const hrs = duration.hours()
  const min = duration.minutes()
  const secs = duration.seconds()

  let timeString = ''
  // include hours
  if (hrs) {
    timeString += `${hrs}h:`
  }
  // include minutes
  if (min) {
    timeString += `${min}m:`
  }
  // include seconds
  if (secs) {
    timeString += `${secs}s`
  }

  return timeString
}

export const getStats = async () => {
  const response: StatData = await http<StatData>('/api/stats')

  if (response) {
    return dataTransformer(response)
  }
}
