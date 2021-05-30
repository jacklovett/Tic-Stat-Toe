export const httpPost = <T>(to: string, data: T) => {
  http(
    new Request(to, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    }),
  )
}

export const http = async <T>(request: RequestInfo): Promise<T> => {
  const response = await fetch(request)
  if (response.ok) {
    return response.json()
  } else {
    throw new Error(response.statusText)
  }
}
