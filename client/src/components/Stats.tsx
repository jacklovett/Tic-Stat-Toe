import { Spinner, SpinnerSize, Stack } from 'office-ui-fabric-react'
import React, { useMemo, useState } from 'react'
import { http } from '../services/httpService'
import { Statistics } from '../types'

const styles = {
  wrapper: {
    root: {
      width: '100%',
    },
  },
  statsPanel: {
    root: {
      width: '100%',
      maxWidth: 600,
      padding: 20,
      background: '#ffffff',
      borderRadius: 5,
    },
  },
}

export const Stats = () => {
  const [isLoading, setLoading] = useState<boolean>(false)
  const [statistics, setStatistics] = useState<Statistics | null>(null)

  useMemo(() => {
    setLoading(true)
    http<Statistics>('/api/stats')
      .then((data: Statistics) => setStatistics(data))
      .catch((error) => console.error(error))
    setLoading(false)
  }, [setStatistics, setLoading])

  const displayStatistics = !isLoading && statistics

  return (
    <Stack
      styles={styles.wrapper}
      grow
      horizontalAlign="center"
      verticalAlign="center"
      verticalFill
    >
      {isLoading && <Spinner size={SpinnerSize.large} />}
      {displayStatistics && <Stack grow styles={styles.statsPanel}></Stack>}
    </Stack>
  )
}
