import Sidebar from "./_components/sidebar"


export default function Layout({ children }: any) {
  return (
    <>
      <Sidebar />
      <div className="pl-0 mt-20 md:mt-0 md:pl-16 flex flex-col md:flex-row h-screen min-h-screen w-screen">
        {children}
      </div>
    </>
  )
}