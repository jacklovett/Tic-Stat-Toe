import React from 'react'
import { Stack, Text } from 'office-ui-fabric-react'

interface Props {
  label: string
  value: string
}

const styles = {
  listItem: {
    root: {
      padding: '20px 10px 20px 10px',
      fontSize: 18,
      borderBottom: 'solid 1px #d9d9d9',
    },
  },
}

export const ListItem = (props: Props) => {
  const { label, value } = props

  return (
    <Stack styles={styles.listItem} horizontal horizontalAlign="space-between">
      <Text>{label}</Text>
      <Text>{value}</Text>
    </Stack>
  )
}
