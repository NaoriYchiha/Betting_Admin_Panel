import '@/styles'
import { Head } from "minista"
import Header from "@/layouts/Header";
import Content from "@/layouts/Content";
import Footer from "@/layouts/Footer";

export default function (props) {
    const {
        children,
        title,
    } = props

    return (
        <>
            <Head htmlAttributes={{ lang: "en" }}>
                <title>BetOps Central | {title}</title>
                <script src="/src/main.js" type="module" />
            </Head>
            <Header/>
            <Content>
                {children}
            </Content>
            <Footer/>
        </>
    )
}
