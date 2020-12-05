export const httpPost = (to: string, data: any) => {
  http(
    new Request(to, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    }),
  ).catch((error) => {
    console.error(error)
  })
}

export const http = async <T>(request: RequestInfo): Promise<T> => {
  const response = await fetch(request)
  return await response.json()
}
