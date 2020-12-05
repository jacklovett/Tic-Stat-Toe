import { Spinner, SpinnerSize, Stack } from 'office-ui-fabric-react'
import React, { useMemo, useState } from 'react'
import { dataTransformer, StatItem, StatData } from '../models/Statistics'
import { http } from '../services/httpService'
import { ListItem } from './ListItem'
import { Title } from './Title'

const styles = {
  wrapper: {
    root: {
      width: '100%',
    },
  },
  statsPanel: {
    root: {
      width: '100%',
      maxWidth: 500,
      padding: 20,
      paddingBottom: 30,
      background: '#ffffff',
      borderRadius: 5,
    },
  },
  title: {
    root: {
      paddingBottom: 20,
      borderBottom: 'solid 1px #d9d9d9',
    },
  },
  statsWrapper: {
    root: {
      overflowY: 'auto',
    },
  },
}

export const Stats = () => {
  const [isLoading, setLoading] = useState<boolean>(false)
  const [statistics, setStatistics] = useState<StatItem[] | null>(null)

  useMemo(() => {
    setLoading(true)

    http<StatData>('/api/stats')
      .then((data: StatData) => setStatistics(dataTransformer(data)))
      .catch((error) => console.error(error))

    setLoading(false)
  }, [setStatistics, setLoading])

  return (
    <Stack
      styles={styles.wrapper}
      horizontalAlign="center"
      verticalAlign="center"
      verticalFill
    >
      {isLoading && <Spinner size={SpinnerSize.large} />}
      {statistics && (
        <Stack styles={styles.statsPanel} verticalFill>
          <Stack styles={styles.title}>
            <Title label="Game Statistics" />
          </Stack>
          <Stack styles={styles.statsWrapper}>
            {statistics.map((statistic: StatItem) => {
              return (
                <ListItem
                  key={statistic.key}
                  label={statistic.label}
                  value={`${statistic.value}`}
                />
              )
            })}
          </Stack>
        </Stack>
      )}
    </Stack>
  )
}
