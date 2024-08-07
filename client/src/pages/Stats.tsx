import React, { useMemo, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { Spinner, SpinnerSize, Stack } from '@fluentui/react'

import { getStats, StatItem } from '../services/statsService'

import { Page, Header, Title, ListItem } from '../components'

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
  const navigate = useNavigate()

  const [isLoading, setLoading] = useState<boolean>(false)
  const [statistics, setStatistics] = useState<StatItem[] | null>(null)
  const [errorMessage, setErrorMessage] = useState<string | undefined>(
    undefined,
  )

  useMemo(async () => {
    setLoading(true)

    try {
      const data = await getStats()

      if (data) {
        setStatistics(data)
      }
    } catch (error) {
      console.error(`Unable to load statistics: ${error}`)
      setErrorMessage('Error: Unable to load statistics')
    } finally {
      setLoading(false)
    }
  }, [])

  return (
    <Page
      header={
        <Header
          navButtonProps={{
            text: 'Back to Game',
            iconName: 'game',
            onClick: () => navigate('/game'),
          }}
        />
      }
      body={
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
                <Title label="Game Statistics" color="#8b07ab" />
              </Stack>
              <Stack styles={styles.statsWrapper}>
                {statistics?.map((statistic: StatItem) => {
                  const { key, label, value } = statistic
                  return <ListItem key={key} label={label} value={`${value}`} />
                })}
              </Stack>
            </Stack>
          )}
        </Stack>
      }
      error={errorMessage}
    />
  )
}
