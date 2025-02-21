"use client";

import { components } from "@/lib/backend/apiV1/schema";
import Link from "next/link";
import { useRouter } from "next/navigation";

export default function ClientPage({
  post,
  me,
}: {
  post: components["schemas"]["PostWithContentDto"];
  me: components["schemas"]["MemberDto"];
}) {
  const router = useRouter();

  return (
    <div>
      <button onClick={() => router.back()}>뒤로가기</button>
      <hr />
      {post.id}번 게시물 상세페이지
      <hr />
      작성날짜 : {post.createDate}
      <hr />
      수정날짜 : {post.modifyDate}
      <hr />
      <h1>{post.title}</h1>
      <hr />
      <p>{post.content}</p>
      {me.id === post.authorId && (
        <div>
          <Link href={`/post/${post.id}/edit`}>수정</Link>
        </div>
      )}
    </div>
  );
}
