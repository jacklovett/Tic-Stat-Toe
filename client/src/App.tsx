import React from 'react'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  RouterProps,
} from 'react-router-dom'
import { IStyle, Stack } from '@fluentui/react'

import { Home } from './pages/Home'
import { PageNotFound } from './pages/PageNotFound'
import { Game } from './pages/Game'
import { Stats } from './pages/Stats'

const styles = {
  app: {
    root: {
      background: '#282c34',
      color: '#0d0d0d',
      height: '100vh',
      overflow: 'hidden',
    } as IStyle,
  },
}

interface Routes {
  name: string
  path: string
  component: (routeProps: RouterProps) => JSX.Element
}

const routes: Routes[] = [
  {
    name: 'stats',
    path: '/stats',
    component: (routeProps: RouterProps) => <Stats {...routeProps} />,
  },
  {
    name: 'game',
    path: '/game',
    component: (routeProps: RouterProps) => <Game {...routeProps} />,
  },
  {
    name: 'home',
    path: '/',
    component: (routeProps: RouterProps) => <Home {...routeProps} />,
  },
  {
    name: '404',
    path: '*',
    component: (routeProps: RouterProps) => <PageNotFound {...routeProps} />,
  },
]

const App = () => (
  <Stack styles={styles.app}>
    <Router>
      <Switch>
        {routes?.map((route: Routes) => {
          const { name, path, component } = route
          return (
            <Route
              key={name}
              exact={name === 'home'}
              path={path}
              render={(routeProps) => component(routeProps)}
            />
          )
        })}
      </Switch>
    </Router>
  </Stack>
)

export default App
