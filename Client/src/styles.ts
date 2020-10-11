import { IStyle } from 'office-ui-fabric-react'

export const iconButton = {
  root: {
    fontSize: 24,
    color: '#ffffff',
    margin: 0,
    padding: 0,
    selectors: {
      '& .ms-Button-icon:hover, .ms-Button-flexContainer:hover': {
        background: '#282c34',
        color: '#09d3ac',
      } as IStyle,
    },
  },
}
