import client from "@/lib/backend/client";
import ClientPage from "./ClientPage";

export default async function Page({ params }: { params: { id: number } }) {
  const { id } = await params;

  const response = await client.GET("/api/v1/posts/{id}", {
    params: {
      path: {
        id,
      },
    },
  });

  if (response.error) {
    return <>{response.error.msg}</>;
  }

  const post = response.data!!;

  return <ClientPage post={post} />;
}
