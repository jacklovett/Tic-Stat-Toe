import React from 'react'
import { Stack, Text } from 'office-ui-fabric-react'

interface Props {
  key: string
  label: string
  value: string
}

const styles = {
  listItem: {
    root: {
      padding: 20,
      fontSize: 18,
      borderBottom: 'solid 1px #d9d9d9',
    },
  },
}

export const ListItem = (props: Props) => {
  const { key, label, value } = props

  return (
    <Stack
      styles={styles.listItem}
      key={key}
      horizontal
      horizontalAlign="space-between"
    >
      <Text>{label}</Text>
      <Text>{value}</Text>
    </Stack>
  )
}
