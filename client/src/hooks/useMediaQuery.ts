import { useEffect, useState } from 'react'

export enum ScreenSize {
  Mobile = 0,
  Tablet = 1,
  Desktop = 2,
}

const tablet = window.matchMedia('(min-width: 767px)')
const desktop = window.matchMedia('(min-width: 1224px)')

export const useMediaQuery = (): ScreenSize => {
  const [screenSize, setScreenSize] = useState<ScreenSize>(ScreenSize.Mobile)

  useEffect(() => {
    const onResize = () => {
      let newScreenSize = ScreenSize.Mobile

      if (tablet.matches) {
        newScreenSize = ScreenSize.Tablet
      }

      if (desktop.matches) {
        newScreenSize = ScreenSize.Desktop
      }

      if (newScreenSize !== screenSize) {
        setScreenSize(newScreenSize)
      }
    }

    onResize()

    window.addEventListener('resize', onResize)

    return () => window.removeEventListener('resize', onResize)
  })

  return screenSize
}
