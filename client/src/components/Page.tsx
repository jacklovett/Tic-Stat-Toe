import { Stack } from 'office-ui-fabric-react'
import React from 'react'

interface IProps {
  header: JSX.Element
  body: JSX.Element
}

export const Page = (props: IProps) => {
  const { body, header } = props
  return (
    <Stack grow verticalFill>
      {header}
      <Stack grow tokens={{ padding: 20 }}>
        {body}
      </Stack>
    </Stack>
  )
}
